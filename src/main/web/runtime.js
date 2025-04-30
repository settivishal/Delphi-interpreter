async function runWasm(wasmFile) {
    try {
        console.log("Loading WASM file:", wasmFile); // Debug log

        const response = await fetch(wasmFile);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // Verify content type
        const contentType = response.headers.get('content-type');
        if (!contentType || !contentType.includes('application/wasm')) {
            const body = await response.text();
            console.error("Received non-WASM response:", body.substring(0, 100));
            throw new Error("Server didn't return WASM content");
        }

        const bytes = await response.arrayBuffer();

        // Verify magic number
        const magic = new Uint8Array(bytes.slice(0, 4));
        if (magic[0] !== 0x00 || magic[1] !== 0x61 || magic[2] !== 0x73 || magic[3] !== 0x6d) {
            throw new Error("Invalid WASM magic number");
        }

        const { instance } = await WebAssembly.instantiate(bytes, {
            env: {
                memory: new WebAssembly.Memory({ initial: 1 }),
                writeln_i32: value => document.getElementById('output').textContent += value + '\n',
                writeln_str: ptr => {
                    const mem = new Uint8Array(instance.exports.memory.buffer);
                    let str = '';
                    for (let i = ptr; mem[i] !== 0; i++) {
                        str += String.fromCharCode(mem[i]);
                    }
                    document.getElementById('output').textContent += str + '\n';
                }
            }
        });

        document.getElementById('output').textContent = '';
        instance.exports.main();

    } catch (err) {
        console.error("WASM Error:", err);
        document.getElementById('output').textContent = `Error: ${err.message}`;
    }
}