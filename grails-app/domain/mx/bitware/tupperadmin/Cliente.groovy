package mx.bitware.tupperadmin

class Cliente {
    String nombre
    String paterno
    String materno

    static constraints = {
        nombre maxSize: 50, nullable: false
        paterno maxSize: 50, nullable: false
        materno maxSize: 50, nullable: true
    }

    String toString() {
        return this.nombre + " " + this.paterno;
    }
}
