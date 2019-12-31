/*
 * Copyright © 2020 Suraj Tiwari (surajtiwari020@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.security.SecureRandom
import java.util.*

val random: SecureRandom = SecureRandom()
const val totalKeys: Int = 5
const val minKeys: Int = 2

fun main() {

    println("1. Encrypt")
    println("2. Decrypt")

    when (readLine()!!) {
        "1" -> encryptData()
        "2" -> decryptData()
        else -> println("default")
    }
}

fun encryptData() {
    // Using hard coded 32 bits :/
    val secretArray: MutableList<ByteArray> = ArrayList()
    secretArray.add("asdfghjklasdfghjklpouytrewdfgrht".toUTF8ByteArray())
    for (sa in secretArray) {
        println("Using \"${sa.toUTF8String()}\" as default secret")
        val secrets = generateKeys(sa)
        for (p in secrets) {
            println(p.key.toString() + " " + p.value.toBase64())
        }
    }
}

fun decryptData() {
    val secretKeys: MutableMap<Int, ByteArray> = mutableMapOf()
    for (a in 1..minKeys) {
        println("Enter key: ")
        secretKeys.plusAssign(readLine()!!.trim().parseToMap())
    }
    if (secretKeys.size > 2) {
        println(getSecret(secretKeys).toUTF8String())
    }
}

// Security Keys for default string "asdfghjklasdfghjklpouytrewdfgrht"
//1 CjHqgYwfqzGIBsevMDPkFXiXmX4dSyJMo7TB2WAJ+6M=
//2 t/djs6qG89+/rwDpys9rlE2BuU2lHdgO8uo1A2mEVcE=
//1 Ha1Vi7eQm62tn55jpzzymPr249+lo3nvfkC5kdCIxb8=
//2 mdQGp9yDk/z1hrJq/9FHlVJDTRTO1m5TUxnFkxKdKfk=
//3 5Qo3Sgx7Yjo0eF9tPordZ8PZ3qQeDGPOSC4YZKVnhDI=
//4 iiag/wqlg15FtOp4TxA2jxkyCpkYPEAwCas9l4236nU=
//5 9viREtpdcpiESgd/jkusfYiomSnI5k2tEpzgYDpNR74=
