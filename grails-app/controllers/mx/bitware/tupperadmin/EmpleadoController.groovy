package mx.bitware.tupperadmin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EmpleadoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Empleado.list(params), model:[empleadoCount: Empleado.count()]
    }

    def show(Empleado empleado) {
        respond empleado
    }

    def create() {
        respond new Empleado(params)
    }

    @Transactional
    def save(Empleado empleado) {
        if (empleado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (empleado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empleado.errors, view:'create'
            return
        }

        empleado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.id])
                redirect empleado
            }
            '*' { respond empleado, [status: CREATED] }
        }
    }

    def edit(Empleado empleado) {
        respond empleado
    }

    @Transactional
    def update(Empleado empleado) {
        if (empleado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (empleado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empleado.errors, view:'edit'
            return
        }

        empleado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.id])
                redirect empleado
            }
            '*'{ respond empleado, [status: OK] }
        }
    }

    @Transactional
    def delete(Empleado empleado) {

        if (empleado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        empleado.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empleado.label', default: 'Empleado'), empleado.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'empleado.label', default: 'Empleado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
