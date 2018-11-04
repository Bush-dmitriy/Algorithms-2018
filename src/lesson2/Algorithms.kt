@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.io.File
import java.io.IOException

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
//T = O(N) R = O(1)
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {

    var min = Integer.MAX_VALUE
    var max = 0
    var lineOfMinValue = 0
    var lineOfMaxValue = 0
    var currentLine = 0
    val e = IOException("Illegal file format")

    for (line in 0 until File(inputName).readLines().size) {
        if (!File(inputName).readLines()[line].matches(Regex("""\d+"""))) throw e
        if (File(inputName).readLines()[line].toInt() < min) {
            min = File(inputName).readLines()[line].toInt()
            currentLine = line
        } else {
            if (File(inputName).readLines()[line].toInt() - min > max) {
                max = File(inputName).readLines()[line].toInt() - min
                lineOfMinValue = currentLine
                lineOfMaxValue = line
            }
        }
    }

    return Pair(lineOfMinValue + 1, lineOfMaxValue + 1)
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
//T = O(firstLength*secondLength) R=O(1)
fun longestCommonSubstring(first: String, second: String): String {

    var currentLength = 0
    var maxLength = 0
    var currentColumn = -1

    for (diagonal in (second.length - 1) downTo 0) {
        var i = 0
        var j = diagonal
        while (j < second.length && i < first.length) {
            if (first[i] == second[j]) {
                currentLength++
                if (j == second.length - 1 || i == first.length - 1) {
                    if (currentLength >= maxLength) {
                        maxLength = currentLength
                        currentColumn = i
                    }
                    currentLength = 0
                }
            } else {
                if (currentLength != 0) {
                    if (currentLength >= maxLength) {
                        maxLength = currentLength
                        currentColumn = i - 1
                    }
                    currentLength = 0
                }
            }
            j++
            i++
        }
    }
    for (diagonal in 1 until (first.length - 1)) {
        var i = diagonal
        var j = 0
        while (j < second.length && i < first.length) {
            if (first[i] == second[j]) {
                currentLength++
                if (j == second.length - 1 || i == first.length - 1) {
                    if (currentLength > maxLength) {
                        maxLength = currentLength
                        currentColumn = i
                    }
                    currentLength = 0
                }
            } else {
                if (currentLength != 0) {
                    if (currentLength > maxLength) {
                        maxLength = currentLength
                        currentColumn = i - 1
                    }
                    currentLength = 0
                }
            }
            j++
            i++
        }
    }
    return if (currentColumn == -1) ""
    else {
        first.substring(currentColumn - (maxLength - 1), currentColumn + 1)
    }
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    TODO()
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    TODO()
}