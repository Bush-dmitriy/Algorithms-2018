package lesson3

/**
 * Этот класс воспринимать только как пример (несмотря на TO DO)
 */
class SampleIterableCollection<T> : AbstractMutableCollection<T>() {

    private class Node<T>(val value: T) {
        var next: Node<T>? = null
    }

    private var start: Node<T>? = null

    override var size = 0

    override fun add(element: T): Boolean {
        val prev = start
        start = Node(element).apply {
            next = prev
        }
        size++
        return true
    }

    override fun iterator(): MutableIterator<T> = SampleIterator()

    inner class SampleIterator : MutableIterator<T> {
        private var current: Node<T>? = null

        private fun findNext(): Node<T>? {
            return if (current != null) current?.next else start
        }

        override fun hasNext(): Boolean {
            return findNext() != null
        }

        override fun next(): T {
            current = findNext() ?: throw NoSuchElementException()
            return current!!.value
        }

        override fun remove() {
            TODO("not implemented")
        }

    }
}