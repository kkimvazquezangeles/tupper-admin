package mx.bitware.tupperadmin

class Color {
    String nombre

    static constraints = {
        nombre maxSize: 30, unique: true, nullable: false
    }

    String toString() {
        return this.nombre;
    }
}
