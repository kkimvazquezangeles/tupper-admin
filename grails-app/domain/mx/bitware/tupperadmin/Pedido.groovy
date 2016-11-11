package mx.bitware.tupperadmin

class Pedido {
    String nombre
    Date fechaInicio
    Date fechaFin
    Empleado empleadoRegistro

    static hasMany = [productos: ProductoPedido]

    static constraints = {
        nombre maxSize: 50, nullable: false
        fechaInicio nullable: false
        fechaFin nullable: false
        empleadoRegistro nullable: false
    }

    String toString() {
        return this.nombre;
    }
}
