import kotlin.system.exitProcess

class VirtualPet(val nome: String) {

    var nivelDeFome = 50
    var nivelFelicidade = 200
    var nivelCansaço = 0
    var nivelIdade = 0
    var vontadeDeIrBanheiro = 0
    var nivelSujeira = 0

    fun alimentar() {
        nivelDeFome = (nivelDeFome - 20).coerceAtLeast(0)
        println("$nome foi alimentado. O nível de fome diminuiu.")
        vontadeDeIrBanheiro += 10

        if (vontadeDeIrBanheiro >= 30) println("$nome precisa ir ao banheiro!")
        if (vontadeDeIrBanheiro > 50) perdeu("$nome está no limite e não aguentou... Você perdeu!")
        if (nivelDeFome >= 100) perdeu("$nome estava com muita fome e não resistiu... Você perdeu!")
    }

    fun brincar() {
        nivelFelicidade += 10
        println("$nome está brincando e se sentindo mais feliz.")
        nivelCansaço += 10
        println("$nome se cansou um pouquinho brincando.")

        nivelSujeira += 10
        if (nivelSujeira >= 40) println("$nome está muito sujo e precisa se limpar!")
        if (nivelSujeira > 50) perdeu("$nome estava sujo demais e ficou doente... Você perdeu!")
    }

    fun verificarStatus() {
        println("\nStatus atual de $nome:")
        println("Nível de fome: $nivelDeFome")
        println("Nível de felicidade: $nivelFelicidade")
        println("Idade: $nivelIdade")
        println("Nível de cansaço: $nivelCansaço")
        println("Necessidade do banheiro: $vontadeDeIrBanheiro")
        println("Nível de sujeira: $nivelSujeira")
    }

    fun passarTempo() {
        nivelDeFome += 3
        println("$nome está ficando mais faminto com o passar do tempo.")

        nivelIdade++
        println("$nome fez mais um ano de idade!")

        nivelFelicidade = (nivelFelicidade - 33).coerceAtLeast(0) //coerceAtLeast(valor) em Kotlin é usado para garantir que um número nunca seja menor do que um determinado valor mínimo.
        nivelCansaço += 10

        if (nivelIdade >= 50) venceu()
        if (nivelFelicidade <= 0) perdeu("$nome ficou muito triste e não resistiu...")
    }

    fun descansar() {
        if (nivelCansaço >= 80) {
            println("$nome descansou e está disposto novamente!")
            nivelCansaço = (nivelCansaço - 40).coerceAtLeast(0)
        } else {
            println("$nome ainda não está cansado o suficiente para descansar.")
        }
    }

    fun limpar() {
        nivelSujeira = (nivelSujeira - 40).coerceAtLeast(0)
        println("$nome está limpinho agora!")
    }

    fun banheiro() {
        vontadeDeIrBanheiro = (vontadeDeIrBanheiro - 30).coerceAtLeast(0)
        println("$nome fez todas as necessidades no banheiro.")
    }

    private fun perdeu(mensagem: String) {
        println(mensagem)
        exitProcess(0)
    }

    private fun venceu() {
        println("VOCÊ VENCEU! Parabéns por cuidar bem de $nome até os 50 anos!")
        exitProcess(0)
    }
}

fun main() {
    println("Bem-vindo ao Simulador de Animal de Estimação Virtual!")
    println("Digite o nome do seu animal de estimação:")
    val nomePet = readLine() ?: "Baltazar Guilherme Tenório"
    val pet = VirtualPet(nomePet)

    while (true) {
        println("\nEscolha uma ação:")
        println("1. Alimentar $nomePet")
        println("2. Brincar com $nomePet")
        println("3. Verificar o status de $nomePet")
        println("4. Descansar")
        println("5. Banho")
        println("6. Banheiro")
        println("7. Sair")

        val escolha = readLine()?.toIntOrNull() ?: continue

        when (escolha) {
            1 -> pet.alimentar()
            2 -> pet.brincar()
            3 -> pet.verificarStatus()
            4 -> pet.descansar()
            5 -> pet.limpar()
            6 -> pet.banheiro()
            7 -> {
                println("Saindo do Simulador de Animal de Estimação Virtual. Adeus!")
                return
            }
            else -> println("Escolha inválida. Tente novamente.")
        }

        pet.passarTempo() // Simula o tempo passando após cada ação
    }
}