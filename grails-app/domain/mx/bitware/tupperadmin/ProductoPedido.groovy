package mx.bitware.tupperadmin

class ProductoPedido {
    static belongsTo = [pedido: Pedido]
    Producto producto
    Pedido pedido
    Empleado empleadoVendio
    double precioCompra
    double precioVenta
    double totalPagado
    double totalGanancia
    String status

    static constraints = {
        producto nullable: false
        pedido nullable: false
        empleadoVendio nullable: false
        status inList: ["COMPRADO", "REGISTRADO", "BODEGA", "VENDIDO"], nullable: false, maxSize: 12
    }

    String toString() {
        return this.producto.nombre + " - " + this.empleadoVendio.nombre + " " + this.empleadoVendio.paterno
    }
}
