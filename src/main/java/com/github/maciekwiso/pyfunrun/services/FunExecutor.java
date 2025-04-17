package com.github.maciekwiso.pyfunrun.services;

import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.python.psi.PyFunction;
import com.jetbrains.python.run.PythonConfigurationType;
import com.jetbrains.python.run.PythonRunConfiguration;

public class FunExecutor {

    private static Logger log = Logger.getInstance(FunExecutor.class);

    public static void execute(PyFunction pyFunction) {
        if (isExecutable(pyFunction)) {
            log.debug("Executing function " + pyFunction.getName());
            var config = createSdkRunConfiguration(pyFunction);
            add(config);
            run(config);
        }
    }

    public static boolean isExecutable(PyFunction pyFunction) {
        return pyFunction != null && pyFunction.getName() != null && pyFunction.getName().startsWith("run");
    }

    private static RunnerAndConfigurationSettings createSdkRunConfiguration(PyFunction pyFunction) {
        var runManager = RunManager.getInstance(pyFunction.getProject());
        var configuration = runManager.createConfiguration("PyFunRun " + pyFunction.getName(), PythonConfigurationType.class);
        var pythonRunConfiguration = (PythonRunConfiguration)configuration.getConfiguration();
        pythonRunConfiguration.setName("PyFunRun " + pyFunction.getName());
        pythonRunConfiguration.setUseModuleSdk(true);
        pythonRunConfiguration.setScriptName(pyFunction.getContainingFile().getName());
        pythonRunConfiguration.setModuleMode(false);
        pythonRunConfiguration.setWorkingDirectory(pyFunction.getContainingFile().getContainingDirectory().getVirtualFile().getPath());
        pythonRunConfiguration.setScriptParameters(pyFunction.getName());
        return configuration;
    }

    private static void add(RunnerAndConfigurationSettings configuration) {
        var runManager = RunManager.getInstance(configuration.getConfiguration().getProject());
        runManager.addConfiguration(configuration);
        runManager.setSelectedConfiguration(configuration);
    }

    private static void run(RunnerAndConfigurationSettings configuration) {
        var executor = DefaultRunExecutor.getRunExecutorInstance();
        ProgramRunnerUtil.executeConfiguration(configuration, executor);
    }
}
