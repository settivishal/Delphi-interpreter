const memory = new WebAssembly.Memory({ initial: 1 });
const imports = { env: { memory } };

async function runWasm(wasmFile) {
    try {
        const response = await fetch(wasmFile);
        const bytes = await response.arrayBuffer();
        const { instance } = await WebAssembly.instantiate(bytes, imports);
        document.getElementById('output').textContent = '';
        instance.exports.main();
    } catch (err) {
        document.getElementById('output').textContent = 'Error: ' + err.message;
    }
}

// Auto-discover WASM files
window.onload = () => {
    fetch('.')
        .then(r => r.text())
        .then(html => {
            const wasmFiles = [...html.matchAll(/href="(.*?\.wasm)"/g)]
                .map(m => m[1]);
            const container = document.getElementById('programs');
            wasmFiles.forEach(file => {
                const btn = document.createElement('button');
                btn.textContent = file;
                btn.onclick = () => runWasm(file);
                container.appendChild(btn);
                container.appendChild(document.createElement('br'));
            });
        });
};