package mx.bitware.tupperadmin

class Empleado {
    String nombre
    String paterno
    String materno
    String sexo
    Date fechaNacimiento

    static constraints = {
        nombre maxSize: 50, nullable: false
        paterno maxSize: 50, nullable: false
        materno maxSize: 50, nullable: true
        sexo inList: ["Femenino", "Masculino"], nullable: false, maxSize: 10
        fechaNacimiento max: new Date(), nullable: false
    }

    String toString() {
        return this.nombre + " " + this.paterno;
    }
}
