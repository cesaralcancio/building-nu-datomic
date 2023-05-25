# building-nu-datomic

A Clojure project designed to learn Datomic through Datomic dev-local library.

## Usage

### Download the dev-local library
Go to [Datomic dev-local page](https://docs.datomic.com/cloud/dev-local.html) and follow the instruction to download and install the dev-tools.
For this project we are using the version **1.0.243** of the dev-local library.

In short, this is a Datomic library that helps you test Datomic in the local environment. It can also be useful for your CI/CD test environment, as this is a "stand alone" version of Datomic. It is important to mention that it doesn't have all Datomic Pro features available (e.g. transaction functions).

### Execute the project

The project was built to be executed as individual functions. This way, we recommend you to start the repl with `lein repl` and invidually run the functions in the repl to test each one individually. Enjoy :)

## License

Copyright © 2023 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
