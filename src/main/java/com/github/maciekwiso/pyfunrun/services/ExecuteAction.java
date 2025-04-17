package com.github.maciekwiso.pyfunrun.services;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.python.psi.PyFunction;
import org.jetbrains.annotations.NotNull;

import static com.github.maciekwiso.pyfunrun.services.FunExecutor.execute;

public class ExecuteAction extends AnAction {
    private static Logger log = Logger.getInstance(ExecuteAction.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        log.debug("ExecuteAction fired");
        var pyFun = pyFunAtCaret(e);
        if (pyFun != null) {
            execute(pyFun);
        }
    }

    private PyFunction pyFunAtCaret(AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        PsiFile psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE);
        if (editor == null || psiFile == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);
        if (element != null) {
            var pyFun = PsiTreeUtil.getParentOfType(element, PyFunction.class);
            return pyFun != null ? pyFun : offset > 1 ? PsiTreeUtil.getParentOfType(psiFile.findElementAt(offset - 1), PyFunction.class) : null;
        }
        return null;
    }
}
