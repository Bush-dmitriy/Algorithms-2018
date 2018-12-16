@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */

/**
 * T = O(N)
 * R = O(N), где N - вершины + ребра графа
 */

fun Graph.findEulerLoop(): List<Graph.Edge> {
    val path: Deque<Graph.Vertex> = LinkedList()
    val path2: Deque<Graph.Vertex> = LinkedList()
    val visited = mutableListOf<Graph.Edge>()
    if (!this.checkEuler()) return listOf()
    val a = this.vertices.first()
    path.addFirst(a)
    while (!path.isEmpty()) {
        val x = path.first
        for (edge in this.getConnections(x).values) {
            if (!visited.contains(edge)) {
                visited.add(edge)
                if (edge.end == path.first) {
                    path.addFirst(edge.begin)
                } else {
                    path.addFirst(edge.end)
                }
                break
            } else {
                if (edge == this.getConnections(x).values.last()) {
                    path2.addFirst(x)
                    path.removeFirst()
                } else {
                    continue
                }
            }
        }
    }
    val listOfEdges = mutableListOf<Graph.Edge>()
    for (i in 0 until path2.size - 1) {
        listOfEdges.add(this.getConnection(path2.toList()[i], path2.toList()[i + 1])
                ?: this.getConnection(path2.toList()[i + 1], path2.toList()[i])!!)
    }
    return listOfEdges
}

fun Graph.checkEuler(): Boolean {
    for (vertex in this.vertices) {
        if (this.getConnections(vertex).size % 2 == 1) return false
    }
    return true
}


/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */

/**
 * T = O(N), где N - кол-во вершин
 * R = O(N)
 */

fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    val thisVertices = this.vertices
    val resultSet: MutableSet<Graph.Vertex> = mutableSetOf()
    val visitedSet: MutableSet<Graph.Vertex> = mutableSetOf()
    var currentVertex: Graph.Vertex
    val queue: Queue<Graph.Vertex> = LinkedList()

    var currentCount = 0
    var currentCount2 = 1
    var temp = 0
    var temp2 = 0

    resultSet.add(this.vertices.first())
    queue.add(this.vertices.first())
    while (!queue.isEmpty()) {
        currentVertex = queue.poll()
        visitedSet.add(currentVertex)

        if (currentCount2 > 0) {
            resultSet.add(currentVertex)
        }
        for (vertex in this.getNeighbors(currentVertex)) {
            if (visitedSet.contains(vertex)) continue
            queue.offer(vertex)
            when{
                currentCount > 0 -> temp2++
                currentCount2 > 0 ->  temp++
            }
        }
        when {
            currentCount > 0 -> currentCount--
            currentCount2 > 0 -> currentCount2--
        }
        if (currentCount == 0 && currentCount2 == 0) {
            currentCount = temp
            temp = 0
            currentCount2 = temp2
            temp2 = 0
        }
    }
    return when {
        resultSet.size >= thisVertices.size / 2 -> resultSet
        else -> {
            thisVertices.removeAll(resultSet)
            thisVertices
        }
    }
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
fun Graph.longestSimplePath(): Path {
    TODO()
}