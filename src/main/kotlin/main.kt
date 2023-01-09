import Product;
import kotlin.system.exitProcess;

val lagerbestand: MutableList<String> = mutableListOf(
    "1;Diesel Jeanshose;Hosen & Unterteile; 49.90",
    "2;Armani Jackenknopf;Jacken & Oberteile;24.99",
    "3;Classic Handschuhe;Unterwäsche & Und..;5.99",
    "4;PallMall Socken XL;Hosen & Unterteile;14.99",
    "5;Welly Jacke XXXL;Jacken & Oberteile;499.90",
    "6;Classic Gürtel 2M;Accessoires & Zub.;19.90",
    "7;Armani Uhr (Geb.);Unterwäsche & Und..;29.90",
    "8;Armani Anzug (Geb.);Anzüge & Access.;1199.00",
    "9;Louis Jacke XL Groß;Jacken & Oberteile;799.00",
    "10;Nike Cap (Schwarz);Accessoires & Zub.;27.99",
)

var warenkorb: MutableList<Product> = mutableListOf()
var produktliste = initialiseProduct(lagerbestand)

fun main() {

    println("Herzlich Willkommen zu Rich-Wear!")

    while(true){
        println()
        println(
            "1- Produktliste anschauen" + "\n" +
            "2- Warenkorb ansehen" + "\n" +
            "3- Bezahlen" + "\n" +
            "4- Shop beenden anschauen" + "\n"
        )

        println("Eingabe (Menüindex): ")
        var eingabe = readln().toInt()
        println("\n")

        when(eingabe){
            1 -> showProductList(produktliste)
            2 -> showCart()
            3 -> payForOrder()
            4 -> exitProcess(0)
        }
    }
}

fun initialiseProduct(produktliste: MutableList<String>): MutableList<Product>{
    var produktListeObj: MutableList<Product> = mutableListOf()

    for(i in produktliste){
        var produktSplitted = i.split(";")
        var newProduct = Product(produktSplitted[0].toInt(), produktSplitted[1], produktSplitted[2], produktSplitted[3].toDouble())
        produktListeObj.add(newProduct)
    }
    return produktListeObj
}

fun showProductList(produktliste: MutableList<Product>){
    println("\n")
    println("ID\tProduktname\t\t\t\t\tKategorie\t\t\t\t\tPreis")
    println("_________________________________________________________________________________")

    for(p in produktliste){
        println("${p.product_id}\t${p.product_name}\t\t\t${p.product_category}\t\t\t${p.product_price} €")
    }

    println("\nWelches Produkt möchtest du dem Warenkorb hinzufügen ?")
    println("Eingabe: ")
    var eingabeWarenkorbHinzuefuegen = readln()

    if(eingabeWarenkorbHinzuefuegen.toInt() > 0) {
        addProductToCart(eingabeWarenkorbHinzuefuegen.toInt())
    }
}

fun showCart(){
    println("\nWarenkorb:")
    println("Produktname\t\t\t\tPreis")
    println("_________________________________________")

    for(p in warenkorb){
        println(p.product_name + "\t\t" + p.product_price + " €")
    }
}

fun addProductToCart(product_id: Int){
    warenkorb.add(produktliste[product_id-1])
}

fun payForOrder(): Boolean{
    var summe: Double = 0.0

    for(p in warenkorb){
        summe += p.product_price
    }

    println("\nDie Endsumme beträgt: $summe")
    println("Bezahlen (Ja | Nein): ")
    var eingabeBezahlen = readln()

    when(eingabeBezahlen){
        "Ja" -> warenkorb.clear(); return true,
        "Nein" -> return false
    }
    return false
}

