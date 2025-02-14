// Generated from e:/Masters/Spring 2025/PLP/Projects/Delphi/src/main/delphi.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link delphiParser}.
 */
public interface delphiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(delphiParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#programHeading}.
	 * @param ctx the parse tree
	 */
	void enterProgramHeading(delphiParser.ProgramHeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#programHeading}.
	 * @param ctx the parse tree
	 */
	void exitProgramHeading(delphiParser.ProgramHeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(delphiParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(delphiParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(delphiParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(delphiParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#usesUnitsPart}.
	 * @param ctx the parse tree
	 */
	void enterUsesUnitsPart(delphiParser.UsesUnitsPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#usesUnitsPart}.
	 * @param ctx the parse tree
	 */
	void exitUsesUnitsPart(delphiParser.UsesUnitsPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterLabelDeclarationPart(delphiParser.LabelDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitLabelDeclarationPart(delphiParser.LabelDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(delphiParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(delphiParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinitionPart(delphiParser.ConstantDefinitionPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinitionPart(delphiParser.ConstantDefinitionPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinition(delphiParser.ConstantDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinition(delphiParser.ConstantDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constantChr}.
	 * @param ctx the parse tree
	 */
	void enterConstantChr(delphiParser.ConstantChrContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constantChr}.
	 * @param ctx the parse tree
	 */
	void exitConstantChr(delphiParser.ConstantChrContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(delphiParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(delphiParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unsignedNumber}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedNumber(delphiParser.UnsignedNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unsignedNumber}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedNumber(delphiParser.UnsignedNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedInteger(delphiParser.UnsignedIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedInteger(delphiParser.UnsignedIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unsignedReal}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedReal(delphiParser.UnsignedRealContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unsignedReal}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedReal(delphiParser.UnsignedRealContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(delphiParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(delphiParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#bool_}.
	 * @param ctx the parse tree
	 */
	void enterBool_(delphiParser.Bool_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#bool_}.
	 * @param ctx the parse tree
	 */
	void exitBool_(delphiParser.Bool_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(delphiParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(delphiParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinitionPart(delphiParser.TypeDefinitionPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinitionPart(delphiParser.TypeDefinitionPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinition(delphiParser.TypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinition(delphiParser.TypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(delphiParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(delphiParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureType}.
	 * @param ctx the parse tree
	 */
	void enterProcedureType(delphiParser.ProcedureTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureType}.
	 * @param ctx the parse tree
	 */
	void exitProcedureType(delphiParser.ProcedureTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void enterType_(delphiParser.Type_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 */
	void exitType_(delphiParser.Type_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(delphiParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(delphiParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void enterScalarType(delphiParser.ScalarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void exitScalarType(delphiParser.ScalarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#subrangeType}.
	 * @param ctx the parse tree
	 */
	void enterSubrangeType(delphiParser.SubrangeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#subrangeType}.
	 * @param ctx the parse tree
	 */
	void exitSubrangeType(delphiParser.SubrangeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdentifier(delphiParser.TypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdentifier(delphiParser.TypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#structuredType}.
	 * @param ctx the parse tree
	 */
	void enterStructuredType(delphiParser.StructuredTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#structuredType}.
	 * @param ctx the parse tree
	 */
	void exitStructuredType(delphiParser.StructuredTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 */
	void enterUnpackedStructuredType(delphiParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 */
	void exitUnpackedStructuredType(delphiParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#stringtype}.
	 * @param ctx the parse tree
	 */
	void enterStringtype(delphiParser.StringtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#stringtype}.
	 * @param ctx the parse tree
	 */
	void exitStringtype(delphiParser.StringtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(delphiParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(delphiParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(delphiParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(delphiParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#indexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexType(delphiParser.IndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#indexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexType(delphiParser.IndexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#componentType}.
	 * @param ctx the parse tree
	 */
	void enterComponentType(delphiParser.ComponentTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#componentType}.
	 * @param ctx the parse tree
	 */
	void exitComponentType(delphiParser.ComponentTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#recordType}.
	 * @param ctx the parse tree
	 */
	void enterRecordType(delphiParser.RecordTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#recordType}.
	 * @param ctx the parse tree
	 */
	void exitRecordType(delphiParser.RecordTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void enterFieldList(delphiParser.FieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void exitFieldList(delphiParser.FieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#fixedPart}.
	 * @param ctx the parse tree
	 */
	void enterFixedPart(delphiParser.FixedPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#fixedPart}.
	 * @param ctx the parse tree
	 */
	void exitFixedPart(delphiParser.FixedPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#recordSection}.
	 * @param ctx the parse tree
	 */
	void enterRecordSection(delphiParser.RecordSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#recordSection}.
	 * @param ctx the parse tree
	 */
	void exitRecordSection(delphiParser.RecordSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variantPart}.
	 * @param ctx the parse tree
	 */
	void enterVariantPart(delphiParser.VariantPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variantPart}.
	 * @param ctx the parse tree
	 */
	void exitVariantPart(delphiParser.VariantPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(delphiParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(delphiParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variant}.
	 * @param ctx the parse tree
	 */
	void enterVariant(delphiParser.VariantContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variant}.
	 * @param ctx the parse tree
	 */
	void exitVariant(delphiParser.VariantContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#setType}.
	 * @param ctx the parse tree
	 */
	void enterSetType(delphiParser.SetTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#setType}.
	 * @param ctx the parse tree
	 */
	void exitSetType(delphiParser.SetTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#fileType}.
	 * @param ctx the parse tree
	 */
	void enterFileType(delphiParser.FileTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#fileType}.
	 * @param ctx the parse tree
	 */
	void exitFileType(delphiParser.FileTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void enterPointerType(delphiParser.PointerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void exitPointerType(delphiParser.PointerTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterProcedureAndFunctionDeclarationPart(delphiParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitProcedureAndFunctionDeclarationPart(delphiParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureOrFunctionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureOrFunctionDeclaration(delphiParser.ProcedureOrFunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureOrFunctionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureOrFunctionDeclaration(delphiParser.ProcedureOrFunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(delphiParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(delphiParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#formalParameterSection}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterSection(delphiParser.FormalParameterSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#formalParameterSection}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterSection(delphiParser.FormalParameterSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#parameterGroup}.
	 * @param ctx the parse tree
	 */
	void enterParameterGroup(delphiParser.ParameterGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#parameterGroup}.
	 * @param ctx the parse tree
	 */
	void exitParameterGroup(delphiParser.ParameterGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(delphiParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(delphiParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constList}.
	 * @param ctx the parse tree
	 */
	void enterConstList(delphiParser.ConstListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constList}.
	 * @param ctx the parse tree
	 */
	void exitConstList(delphiParser.ConstListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#resultType}.
	 * @param ctx the parse tree
	 */
	void enterResultType(delphiParser.ResultTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#resultType}.
	 * @param ctx the parse tree
	 */
	void exitResultType(delphiParser.ResultTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(delphiParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(delphiParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unlabelledStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnlabelledStatement(delphiParser.UnlabelledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unlabelledStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnlabelledStatement(delphiParser.UnlabelledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(delphiParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(delphiParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(delphiParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(delphiParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#relationaloperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationaloperator(delphiParser.RelationaloperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#relationaloperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationaloperator(delphiParser.RelationaloperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(delphiParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(delphiParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#additiveoperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveoperator(delphiParser.AdditiveoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#additiveoperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveoperator(delphiParser.AdditiveoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(delphiParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(delphiParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#multiplicativeoperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeoperator(delphiParser.MultiplicativeoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#multiplicativeoperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeoperator(delphiParser.MultiplicativeoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#signedFactor}.
	 * @param ctx the parse tree
	 */
	void enterSignedFactor(delphiParser.SignedFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#signedFactor}.
	 * @param ctx the parse tree
	 */
	void exitSignedFactor(delphiParser.SignedFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(delphiParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(delphiParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#unsignedConstant}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedConstant(delphiParser.UnsignedConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#unsignedConstant}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedConstant(delphiParser.UnsignedConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#functionDesignator}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDesignator(delphiParser.FunctionDesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#functionDesignator}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDesignator(delphiParser.FunctionDesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(delphiParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(delphiParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#set_}.
	 * @param ctx the parse tree
	 */
	void enterSet_(delphiParser.Set_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#set_}.
	 * @param ctx the parse tree
	 */
	void exitSet_(delphiParser.Set_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(delphiParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(delphiParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(delphiParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(delphiParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#procedureStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcedureStatement(delphiParser.ProcedureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#procedureStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcedureStatement(delphiParser.ProcedureStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#actualParameter}.
	 * @param ctx the parse tree
	 */
	void enterActualParameter(delphiParser.ActualParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#actualParameter}.
	 * @param ctx the parse tree
	 */
	void exitActualParameter(delphiParser.ActualParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#parameterwidth}.
	 * @param ctx the parse tree
	 */
	void enterParameterwidth(delphiParser.ParameterwidthContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#parameterwidth}.
	 * @param ctx the parse tree
	 */
	void exitParameterwidth(delphiParser.ParameterwidthContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void enterGotoStatement(delphiParser.GotoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void exitGotoStatement(delphiParser.GotoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement_(delphiParser.EmptyStatement_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#emptyStatement_}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement_(delphiParser.EmptyStatement_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#empty_}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_(delphiParser.Empty_Context ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#empty_}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_(delphiParser.Empty_Context ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#structuredStatement}.
	 * @param ctx the parse tree
	 */
	void enterStructuredStatement(delphiParser.StructuredStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#structuredStatement}.
	 * @param ctx the parse tree
	 */
	void exitStructuredStatement(delphiParser.StructuredStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(delphiParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(delphiParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#conditionalStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionalStatement(delphiParser.ConditionalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#conditionalStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionalStatement(delphiParser.ConditionalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(delphiParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(delphiParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#caseListElement}.
	 * @param ctx the parse tree
	 */
	void enterCaseListElement(delphiParser.CaseListElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#caseListElement}.
	 * @param ctx the parse tree
	 */
	void exitCaseListElement(delphiParser.CaseListElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#repetetiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepetetiveStatement(delphiParser.RepetetiveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#repetetiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepetetiveStatement(delphiParser.RepetetiveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStatement(delphiParser.RepeatStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStatement(delphiParser.RepeatStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#forList}.
	 * @param ctx the parse tree
	 */
	void enterForList(delphiParser.ForListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#forList}.
	 * @param ctx the parse tree
	 */
	void exitForList(delphiParser.ForListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#initialValue}.
	 * @param ctx the parse tree
	 */
	void enterInitialValue(delphiParser.InitialValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#initialValue}.
	 * @param ctx the parse tree
	 */
	void exitInitialValue(delphiParser.InitialValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#finalValue}.
	 * @param ctx the parse tree
	 */
	void enterFinalValue(delphiParser.FinalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#finalValue}.
	 * @param ctx the parse tree
	 */
	void exitFinalValue(delphiParser.FinalValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void enterWithStatement(delphiParser.WithStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void exitWithStatement(delphiParser.WithStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#recordVariableList}.
	 * @param ctx the parse tree
	 */
	void enterRecordVariableList(delphiParser.RecordVariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#recordVariableList}.
	 * @param ctx the parse tree
	 */
	void exitRecordVariableList(delphiParser.RecordVariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(delphiParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(delphiParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#ancestorList}.
	 * @param ctx the parse tree
	 */
	void enterAncestorList(delphiParser.AncestorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#ancestorList}.
	 * @param ctx the parse tree
	 */
	void exitAncestorList(delphiParser.AncestorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(delphiParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(delphiParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void enterVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#visibilitySection}.
	 * @param ctx the parse tree
	 */
	void exitVisibilitySection(delphiParser.VisibilitySectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassMember(delphiParser.ClassMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassMember(delphiParser.ClassMemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(delphiParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(delphiParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(delphiParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link delphiParser#destructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDestructorDeclaration(delphiParser.DestructorDeclarationContext ctx);
}