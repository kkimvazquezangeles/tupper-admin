package mx.bitware.tupperadmin

class Producto {
    String codigo
    String nombre
    Color color
    String descripcion

    static constraints = {
        codigo maxSize: 10, unique: true, nullable: false
        nombre maxSize: 50, nullable: false
        color nullable: false
        descripcion nullable: true, maxSize:250
    }

    String toString() {
        return this.nombre + " " + this.codigo;
    }
}
