# IntelliKube

An IntelliJ plugin to make development faster.

## Roadmap
**This roadmap will change over time.**

- [X] Custom file icons for Kube files
- [ ] Support for `kubejs.plugins.txt`
    - When a class impls `KubeJSPlugin` and it is not inside the plugin file, it would insert the class path and the mod id from Gradle properties
  - [ ] Syntax highlighting
- [ ] Support for `kubejs.bindings.txt`
  - [ ] Syntax highlighting
  - [ ] Tooltips for adding classes, methods, and fields
    - Just like in Minecraft Development Plugin, you can right-click to generate an accessor or AT entry
  - [ ] Validation to check that the stuff above does exist
- [ ] Recipe schema validation
- [ ] Navigation and usage linking in the txt file via `Crtl + Click`
- [ ] JSON schema validation and completion
  - [ ] Snippets if possible
- [ ] JSONC support
  - Could just ignore comments when validation JSONs