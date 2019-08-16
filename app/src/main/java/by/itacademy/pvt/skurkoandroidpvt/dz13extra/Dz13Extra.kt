package by.itacademy.pvt.skurkoandroidpvt.dz13extra

class Dz13Extra {

    fun checkWin(codeList: List<List<String>>, shoppingCart: List<String>): Int {

        val shoppingCartSize = shoppingCart.size
        val codeListSize = codeList.sumBy { it.size }

        var counter = 0
        var iter = 0
        var index = 0

        if (shoppingCartSize < codeListSize)
            return 0

        while (iter < codeListSize) {
            if (shoppingCart[iter] == codeList[index][0]) {
                for (j in 0 until codeList[index].size) {
                    if (shoppingCart[iter] == codeList[index][j] || codeList[index][j] == "anything") {
                        counter++
                        iter++
                    } else {
                        counter -= j
                        index--
                        break
                    }
                }
                index++
            } else iter++
        }
        return if (counter == codeListSize) 1 else 0
    }
}