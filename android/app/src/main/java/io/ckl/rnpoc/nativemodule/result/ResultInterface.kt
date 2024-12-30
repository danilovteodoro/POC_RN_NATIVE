package io.ckl.rnpoc.nativemodule.result

import io.ckl.rnpoc.model.Person

interface ResultInterface {
     fun resultString(moduleName: String, s: String)
     fun resultPerson(moduleName: String, p: Person)
}