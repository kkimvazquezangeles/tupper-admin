package mx.bitware.tupperadmin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Producto.list(params), model:[productoCount: Producto.count()]
    }

    def show(Producto producto) {
        respond producto
    }

    def create() {
        respond new Producto(params)
    }

    @Transactional
    def save(Producto producto) {
        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'create'
            return
        }

        producto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
                redirect producto
            }
            '*' { respond producto, [status: CREATED] }
        }
    }

    def edit(Producto producto) {
        respond producto
    }

    @Transactional
    def update(Producto producto) {
        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'edit'
            return
        }

        producto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
                redirect producto
            }
            '*'{ respond producto, [status: OK] }
        }
    }

    @Transactional
    def delete(Producto producto) {

        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        producto.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
