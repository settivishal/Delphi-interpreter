// Generated from /Users/jaiharishan/Desktop/PLP1/src/main/delphi.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link delphiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface delphiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link delphiParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(delphiParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#programHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeading(delphiParser.ProgramHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(delphiParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(delphiParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBlock(delphiParser.ClassBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#usesUnitsPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsesUnitsPart(delphiParser.UsesUnitsPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelDeclarationPart(delphiParser.LabelDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(delphiParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinitionPart(delphiParser.ConstantDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(delphiParser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constantChr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantChr(delphiParser.ConstantChrContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(delphiParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unsignedNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedNumber(delphiParser.UnsignedNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unsignedInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInteger(delphiParser.UnsignedIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unsignedReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedReal(delphiParser.UnsignedRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(delphiParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#bool_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_(delphiParser.Bool_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(delphiParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionPart(delphiParser.TypeDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(delphiParser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(delphiParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureType(delphiParser.ProcedureTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(delphiParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(delphiParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarType(delphiParser.ScalarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubrangeType(delphiParser.SubrangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(delphiParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#structuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredType(delphiParser.StructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpackedStructuredType(delphiParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#stringtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringtype(delphiParser.StringtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(delphiParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(delphiParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(delphiParser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#componentType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponentType(delphiParser.ComponentTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#recordType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordType(delphiParser.RecordTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#fieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldList(delphiParser.FieldListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#fixedPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFixedPart(delphiParser.FixedPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#recordSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordSection(delphiParser.RecordSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variantPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariantPart(delphiParser.VariantPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(delphiParser.TagContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariant(delphiParser.VariantContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#setType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetType(delphiParser.SetTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(delphiParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#fileType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileType(delphiParser.FileTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#pointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerType(delphiParser.PointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationPart(delphiParser.VariableDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(delphiParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureAndFunctionDeclarationPart(delphiParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureOrFunctionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureOrFunctionDeclaration(delphiParser.ProcedureOrFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(delphiParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(delphiParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#formalParameterSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterSection(delphiParser.FormalParameterSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#parameterGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterGroup(delphiParser.ParameterGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(delphiParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstList(delphiParser.ConstListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(delphiParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#methodImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodImplementation(delphiParser.MethodImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#constructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorImplementation(delphiParser.ConstructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#destructorImplementation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestructorImplementation(delphiParser.DestructorImplementationContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#resultType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultType(delphiParser.ResultTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclarationPart(delphiParser.ClassDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(delphiParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unlabelledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlabelledStatement(delphiParser.UnlabelledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(delphiParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classMethodStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMethodStatement(delphiParser.ClassMethodStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(delphiParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(delphiParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(delphiParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#relationaloperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationaloperator(delphiParser.RelationaloperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(delphiParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#additiveoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveoperator(delphiParser.AdditiveoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(delphiParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#multiplicativeoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeoperator(delphiParser.MultiplicativeoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#signedFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedFactor(delphiParser.SignedFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(delphiParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#unsignedConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedConstant(delphiParser.UnsignedConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#functionDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDesignator(delphiParser.FunctionDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(delphiParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#classMethodDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMethodDesignator(delphiParser.ClassMethodDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#set_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_(delphiParser.Set_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#elementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementList(delphiParser.ElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(delphiParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#procedureStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureStatement(delphiParser.ProcedureStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#actualParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameter(delphiParser.ActualParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#parameterwidth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterwidth(delphiParser.ParameterwidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#gotoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoStatement(delphiParser.GotoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#emptyStatement_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement_(delphiParser.EmptyStatement_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#empty_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty_(delphiParser.Empty_Context ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#structuredStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredStatement(delphiParser.StructuredStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(delphiParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(delphiParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#conditionalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalStatement(delphiParser.ConditionalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(delphiParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(delphiParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#caseListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseListElement(delphiParser.CaseListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#repetetiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetetiveStatement(delphiParser.RepetetiveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(delphiParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(delphiParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(delphiParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#forList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForList(delphiParser.ForListContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#initialValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialValue(delphiParser.InitialValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#finalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinalValue(delphiParser.FinalValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#withStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithStatement(delphiParser.WithStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link delphiParser#recordVariableList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordVariableList(delphiParser.RecordVariableListContext ctx);
}