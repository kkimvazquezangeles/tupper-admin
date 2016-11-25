package mx.bitware.tupperadmin

class ProductoPedido {
    static belongsTo = [pedido: Pedido]
    Producto producto
    Pedido pedido
    Empleado empleadoVendio
    Cliente cliente
    double precioFactura
    double precioCatalogo
    double totalPagado
    double totalGanancia
    String status

    double getTotalGanancia() {
        totalGanancia = precioCatalogo - precioFactura
        totalGanancia.round(2)
    }

    static transients = ['totalGanancia']

    static constraints = {
        producto nullable: false
        pedido nullable: false
        empleadoVendio nullable: false
        cliente nullable: true
        status inList: ["COMPRADO", "REGISTRADO", "BODEGA", "VENDIDO"], nullable: false, maxSize: 12
        totalGanancia display: true
    }

    String toString() {
        if(this.producto == null || this.empleadoVendio == null) {
            return ""
        }
        return this.producto.nombre + " - " + this.empleadoVendio.nombre + " " + this.empleadoVendio.paterno
    }
}
