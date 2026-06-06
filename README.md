# IntelliKube

An IntelliJ plugin to make development faster.

## Roadmap
**This roadmap will change over time.**

- [X] Custom file icons for Kube files
- [ ] Support for `kubejs.plugins.txt`
    - [ ] `Crtl + Click` on class path to go to that class
    - [X] Generation of the file and class path insertion
      - [ ] Insert mod id if possible
    - [ ] Syntax highlighting
- [ ] Support for `kubejs.bindings.txt`
  - [ ] Syntax highlighting
  - [ ] Tooltips for adding classes, methods, and fields
    - Just like in Minecraft Development Plugin, you can right-click to generate an accessor or AT entry
  - [ ] Validation to check that the stuff above does exist
  - [ ] `Crtl + Click` on classes, methods, etc to go there
- [ ] Recipe schema validation
- [ ] Navigation and usage linking in the txt file via `Crtl + Click`
- [ ] JSON schema validation and completion
  - [ ] Snippets if possible
- [ ] JSONC support
  - Could just ignore comments when validation JSONs