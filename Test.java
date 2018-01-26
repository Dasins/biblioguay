
public class Test
{
    OrganizadorLibros libreria;
    
    public Test() {
        libreria = new OrganizadorLibros();
    }
    
    public void anadirAlgunosLibros() {
        libreria.addLibro("9788431615345","El Conde Lucanor","Don Juan Manuel", 2014, 208);
        libreria.addLibro("9788431635176","Zalacain el Aventurero","Pio Baroja", 2014, 272);
        libreria.addLibro("9788441421745","La Dama del Alba","Alex Casona", 2006, 144);
        libreria.addLibro("9788441421745","La Dama del Alva","Alex Casona", 2006, 144);
        libreria.addLibro("9788441421745","La Dama del Alba","Alex Casona", 2006, 144);
        libreria.addLibro("9788466716802","Flor de Leyendas","Alex Casona", 2004, 224);
        libreria.addLibro("9788441421523","La Barca Sin Pescador","Alex Casona", 2009, 104);
        libreria.addLibro("9788437605227","El Arbol de la Ciencia","Pio Baroja", 2004, 304);
        libreria.addLibro("9788401019180","Las Vrumaz del Miedo","Rafael Abalos", 2017, 480);
        libreria.addLibro("9788401019180","Las Vrumas del Miedo","Rafael Abalos", 2017, 480);
        libreria.addLibro("9788401019180","Las Brumaz del Miedo","Rafael Abalos", 2017, 480);
        libreria.addLibro("9788401019180","Las Brumas del Miedo","Rafael Abalos", 2017, 480);
        libreria.addLibro("9788401019180","Las Brumas del Miedo","Rafael Abalos", 2017, 480);
        System.out.println("Se han anadido algunos libros");
    }
    
    public void mostrarLibros() {
        libreria.mostrarTodos();
    }
    
    public void ordenarPorTitulo() {
        System.out.println("\n\n Ordenando por titulo del libro \n");
        libreria.ordenarPor("titulo");
    }
    
    public void ordenarPorAnoInversamente() {
        System.out.println("\n\n Ordenando por ano  del libro (orden inverso, menor a mayor) \n");
        libreria.ordenarDescendentementePor("ano"); 
    }
    
    public void cambiandoAlgunosValores() {
        System.out.println("\n\n Cambiando valores de 3, 4, 5, 8, 9,10 libro \n");
        libreria.setTitulo(3,"Java el Hutt");
        libreria.setTitulo(4,"Paco el Terrible");
        libreria.setTitulo(8,"El cuento cansino");
        libreria.setTitulo(9,"Kaos Etiliko");
        libreria.setTitulo(10,"Catpataz el gato");
        libreria.setIsbn(10,"Hola mundo!!");
        libreria.setAno(5,2009);
        libreria.mostrarTodos();
    }
    
    public void borrarLibros2017() {
        System.out.println("\n\n Borrando los libros de 2017 \n");
        libreria.borrarLibrosDelAno(2017);
        libreria.mostrarTodos();
    }
}
