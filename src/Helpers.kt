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
import java.util.*

fun String.toByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}

fun ByteArray.toBase64(): String {
    return Base64.getEncoder().encodeToString(this)
}

fun ByteArray.toUTF8String(): String {
    return this.toString(Charsets.UTF_8)
}

fun String.toUTF8ByteArray(): ByteArray {
    return this.toByteArray(Charsets.UTF_8)
}

fun String.parseToMap(): Pair<Int, ByteArray> {
    val data = this.split(" ")
    return data[0].toInt() to data[1].toByteArray()
}