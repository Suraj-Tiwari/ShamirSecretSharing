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
import kotlin.collections.HashMap

fun generate(random: SecureRandom, degree: Int, x: Byte): ByteArray {
    val p = ByteArray(degree + 1)
    do {
        random.nextBytes(p)
    } while (degree(p) != degree)
    p[0] = x
    return p
}

fun generateKeys(secret: ByteArray): Map<Int, ByteArray> {
    val values = Array<ByteArray>(totalKeys) { ByteArray(secret.size) }
    for (i in secret.indices) {
        val p = generate(random, minKeys - 1, secret[i])
        for (x in 1..totalKeys) {
            values[x - 1][i] = eval(p, x.toByte())
        }
    }
    val parts = HashMap<Int, ByteArray>(totalKeys)
    for (i in values.indices) {
        parts[i + 1] = values[i]
    }
    return Collections.unmodifiableMap<Int, ByteArray>(parts)
}

fun getSecret(parts: Map<Int, ByteArray>): ByteArray {
    if (parts.size < 0) {
        println("Invalid Secrets")
        throw NullPointerException()
    }
    val lengths = parts.values.stream().mapToInt { v -> v.size }.distinct().toArray()
    if (lengths.size != 1) {
        println("Invalid length")
        throw NullPointerException()
    }
    val secret = ByteArray(lengths[0])
    for (i in secret.indices) {
        val points = Array<ByteArray>(parts.size) { ByteArray(2) }
        for ((j, part) in parts.entries.withIndex()) {
            points[j][0] = part.key.toByte()
            points[j][1] = part.value[i]
        }
        secret[i] = interpolate(points)
    }
    return secret
}