package com.github.maciekwiso.pyfunrun.startup;

import com.github.maciekwiso.pyfunrun.MyBundle;
import com.github.maciekwiso.pyfunrun.services.FunRunner;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilCore;
import com.jetbrains.python.PyTokenTypes;
import com.jetbrains.python.PythonLanguage;
import com.jetbrains.python.psi.PyFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.github.maciekwiso.pyfunrun.services.FunRunner.run;
import static com.intellij.icons.AllIcons.Actions.Execute;

public class PyFunRunLineMarkerContributor extends RunLineMarkerContributor {

    private static Logger log = Logger.getInstance(PyFunRunLineMarkerContributor.class);

    @Override
    public @Nullable Info getInfo(@NotNull PsiElement element) {
        if (!element.getLanguage().isKindOf(PythonLanguage.INSTANCE)) return null;
        if (PsiUtilCore.getElementType(element) != PyTokenTypes.DEF_KEYWORD) return null;
        var pyFunction = PsiTreeUtil.getParentOfType(element, PyFunction.class) != null ? PsiTreeUtil.getParentOfType(element, PyFunction.class) : null;
        if (!FunRunner.isRunnable(pyFunction)) return null;
        log.debug("Adding gutter runner for: " + pyFunction);
        var actions = new AnAction[] {
        new AnAction() {
            @Override
            public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
                log.debug("Gutter action fired for " + pyFunction.getName());
                run(pyFunction);
            }
        }};
        return new Info(Execute, actions, psiElement -> MyBundle.message("run.button.text"));
    }
}
