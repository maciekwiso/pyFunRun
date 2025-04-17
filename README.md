# PyFunRun

![Build](https://github.com/maciekwiso/pyFunRun/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/27091-pyfunrun.svg)](https://plugins.jetbrains.com/plugin/27091-pyfunrun)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/27091-pyfunrun.svg)](https://plugins.jetbrains.com/plugin/27091-pyfunrun)

<!-- Plugin description -->
This plugin allows you to run any Python function with no params as if it was the main function in the file.

It's very useful if you have a lot of small python utility functions and want to be able to run them quickly.

Next to each runnable function you will see run button and you can also use shortcut ctrl + alt + r

Just make sure the function has no params and name starts with "run" and then place this snippet at the end of the .py file:

```
import sys
globals()[sys.argv[1]]()
```
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "PyFunRun"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/27091-pyfunrun) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/27091-pyfunrun/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/maciekwiso/pyFunRun/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
