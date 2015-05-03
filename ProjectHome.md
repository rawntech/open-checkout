# Open Checkout #

**Open Checkout** is derived from _[Openbravo POS 2.32](http://sourceforge.net/projects/openbravopos/)_

_Openbravo POS_ hasn't received major updates since 2009.

**Open Checkout** is focused in bringing the same features as _Openbravo POS_ plus a 'plugin manager support', so it will be possible to add custom features e.g. automatic updates, electronic invoices, etc.

**Open Checkout** is extensible while maintaining compatibility with _Openbravo POS_, so when an update comes from _Openbravo POS_ it should be easy to merge into this project.


---


List of things to do:

- Modify Openbravo POS 2.32:
  * Database normalization so it can support electronic invoices
  * Make some cool logos and images for this project
  * Change vendor logos and images
  * Release Open Checkout API (java like) based on the vendor product
- Develop plugin support:
  * Define plugin interface
  * Change main classes so their instances can be modified by a plugin
  * Update API
- Develop plugin manager:
  * Design plugin manager GUI
  * Integrate to Open Checkout
- Develop automatic updates plugin
  * Define main server for this purpose

Plugin development is up to everyone.