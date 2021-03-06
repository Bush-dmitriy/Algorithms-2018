@file:Suppress("UNUSED_PARAMETER")

package lesson6


/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */

/**
 * T = O(N*M), где N - first.length, M - second.length
 * R = O(N*M), где N - first.length, M - second.length
 */

fun longestCommonSubSequence(first: String, second: String): String {
    var result = ""
    val max = Array(first.length + 1) { IntArray(second.length + 1) }
    for (i in 1 until max.size) {
        for (j in 1 until max[i].size) {
            if (first[i - 1] == second[j - 1]) {
                max[i][j] = 1 + max[i - 1][j - 1]
            } else max[i][j] = Math.max(max[i - 1][j], max[i][j - 1])
        }
    }
    var i = first.length
    var j = second.length
    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                result = first[i - 1] + result
                i--
                j--
            }
            max[i][j] == max[i - 1][j] -> i--
            else -> j--
        }
    }
    return result
}


/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */

/**
 * T = O(n^2)
 * R = O(n)
 */

fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    if (list.isEmpty()) return result
    val indexes = IntArray(list.size) { -1 }
    val lengths = IntArray(list.size) { 1 }
    for (i in 0 until list.size) {
        for (j in 0 until i) {
            if (list[j] < list[i] && lengths[j] + 1 > lengths[i]) {
                lengths[i] = lengths[j] + 1
                indexes[i] = j
            }
        }
    }
    var length = 0
    var position = 0
    for (i in 0 until lengths.size) {
        if (lengths[i] > length) {
            position = i
            length = lengths[i]
        }
    }
    while (position != -1) {
        result.add(0, list[position])
        position = indexes[position]
    }
    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5