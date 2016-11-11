package mx.bitware.tupperadmin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductoPedidoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductoPedido.list(params), model:[productoPedidoCount: ProductoPedido.count()]
    }

    def show(ProductoPedido productoPedido) {
        respond productoPedido
    }

    def create() {
        respond new ProductoPedido(params)
    }

    @Transactional
    def save(ProductoPedido productoPedido) {
        if (productoPedido == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productoPedido.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productoPedido.errors, view:'create'
            return
        }

        productoPedido.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productoPedido.label', default: 'ProductoPedido'), productoPedido.id])
                redirect productoPedido
            }
            '*' { respond productoPedido, [status: CREATED] }
        }
    }

    def edit(ProductoPedido productoPedido) {
        respond productoPedido
    }

    @Transactional
    def update(ProductoPedido productoPedido) {
        if (productoPedido == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productoPedido.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productoPedido.errors, view:'edit'
            return
        }

        productoPedido.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productoPedido.label', default: 'ProductoPedido'), productoPedido.id])
                redirect productoPedido
            }
            '*'{ respond productoPedido, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductoPedido productoPedido) {

        if (productoPedido == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productoPedido.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productoPedido.label', default: 'ProductoPedido'), productoPedido.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productoPedido.label', default: 'ProductoPedido'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
