import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Crea objetos que simulan un organizador de libros.
 * 
 *
 * Inicio:12:12
 * Ultimo commit: 
 *
 * @author Dasins
 * @version 16/02/2018
 */
public class OrganizadorLibros {
    // Coleccion de libros
    private ArrayList<Libro> libros;
    // Identificador unico de cada libro.
    int id;
    
    /**
     * Construye un organizador de libros vacios
     */
    public OrganizadorLibros() {
        libros = new ArrayList<>();
        id = 0;
    }
    
    /**
     * Construye un organizador de libros a partir de un archivo de texto.
     * El formato del archivo de texto debe ser 
     */
    public OrganizadorLibros(String rutaArchivo) {
        libros = new ArrayList<>();
        id = 0;
        addLibro(rutaArchivo);
    }
    
    /**
     * Anade un libro a la coleccion. 
     * Cada nuevo libro lleva un id distinto y consecutivo al anterior.
     * @param isbn El isbn.
     * @param titulo El titulo del libro.
     * @param autor El autor/autores del libro.
     * @param numPags El numero de paginas del libro.
     */
    public void addLibro(String isbn, String titulo, String autor, int ano, int numPags) {
        libros.add(new Libro(id, isbn, titulo, autor, ano, numPags));
        id++;
    }
    
    /**
     * Anade todos los libros listados en un archivo de texto.
     */
    public void addLibro(String rutaArchivo){
        try {
            File archivo = new File(rutaArchivo);
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                libros.add(extraerInfoLibro(sc.nextLine()));
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Extrae la informacion del escaner y la convierte en un libro
     */
    private Libro extraerInfoLibro(String infoLibro) {
        Libro libro = null;
        String[] partes = infoLibro.split(" # ");
        libro = new Libro(id, partes[1], partes[0], partes[2],Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
        id++;
        return libro;
    }
    
    /**
     * Muestra los libros de cada ano:
     */
    public void mostrarPorAno() {
        ArrayList<Libro> copiaLibros = new ArrayList<>(libros);
        System.out.println("ID\tISBN\t\tAutor/es\t\tPags\tAno\tTitulo");
        while(copiaLibros.size() > 0){
            int buscado = copiaLibros.get(0).getAno();
            System.out.println(buscado + ":");
            for(int i = 0; i < copiaLibros.size(); i++){
                Libro libro = copiaLibros.get(i);
                if(libro.getAno() == buscado){
                    System.out.println(libro);
                    copiaLibros.remove(libro);
                    i--;
                }
            }
        }
    }
    
    /**
     * @return Devuelve TRUE si el indice es valido para la coleccion libros. FALSE si no lo es.
     */
    private boolean validarIndex(int index) {
        boolean validarIndice = false;
        if(index < libros.size()){
            validarIndice = true;
        }
        return validarIndice;
    }
    
    /**
     * Imprime en la terminal toda la informacion de la coleccion de libros indicada por parametro.
     */
    private void imprimir(ArrayList<Libro> librosAImprimir){
        if(librosAImprimir.size() > 0){
            System.out.println("ID\tISBN\t\tAutor/es\t\tPags\tAno\tTitulo");
            for(Libro libro : librosAImprimir){
                System.out.println(libro);
            }
        }
    }
    
    /**
     * Ordena una coleccion de objetos Libro segun uno de sus atributos indicados por parametro.
     * @param atrib El atributo del objeto libro segun el que se va ordenar. Ver clase Libro para +info.
     * @param ordenInverso TRUE orden inveso, FALSE orden normal (alfabeticamente o mayor a menor).
     */
    private ArrayList<Libro> orderBy(String atributo, boolean ordenInverso) {
        ArrayList<Libro> arrayADevolver = new ArrayList<>();
        ArrayList<Libro> copiaLibros = (ArrayList<Libro>) libros.clone();
        for(int i = 0; i < copiaLibros.size(); i = 0) {
            Libro primerLibro = copiaLibros.get(i); //El libro que debe ser impreso en primer lugar cada iteracion.
            for(int j = 0; j < copiaLibros.size(); j++) {
                Libro libroActual = copiaLibros.get(j);
                boolean comparador = false;
                if(ordenInverso) {
                    comparador = primerLibro.compareTo(libroActual, atributo);
                }
                else {
                    comparador = !primerLibro.compareTo(libroActual, atributo);
                }
                if(comparador) {
                    primerLibro = libroActual;
                }
            }
            arrayADevolver.add(primerLibro);
            copiaLibros.remove(primerLibro);
        }
        return arrayADevolver;
    }
    
    /**
     * Imprime la informacion de todos los libros por la teminal
     */
    public void mostrarTodos() {
        imprimir(libros);
    }
    
    /**
     * Imprime por terminal toda la informacion de todos los libros ordenada por un atributo indicado por parametro.
     * Los posibles atributos son: id, isbn, titulo, autor, ano, numpags.
     * Si el atributo introducido no es valido, imprime la informacion sin ordenar.
     * El orden normal es Mayor a menor y albafeticamente
     * 
     * @param atributo El atributo por el que se quiere ordenar
     */
    public void ordenarPor(String atributo) {
        imprimir(orderBy(atributo,false));
    }
    
    /**
     * Imprime por terminal toda la informacion de todos los libros ordenada inversamente por un atributo indicado por parametro.
     * Los posibles atributos son: id, isbn, titulo, autor, ano, numpags.
     * Si el atributo introducido no es valido, imprime la informacion sin ordenar.
     * El orden normal es Mayor a menor y albafeticamente.
     * 
     * @param atributo El atributo por el que se quiere ordenar
     */
    public void ordenarDescendentementePor(String atributo) {
        imprimir(orderBy(atributo,true));
    }
    
    /**
     * Cambia el valor del isbn del libro que ocupa la posicion indicada por parametro.
     * @param isbn El nuevo isbn.
     * @param id El ID del libro a modificar.
     */
    public void setIsbn(int index, String isbn) {
        int i = 0;
        while(i < libros.size()) {
            Libro libroActual = libros.get(i);
            if(libroActual.getID() == id){
                    libros.get(index).setIsbn(isbn);
                    i = libros.size();
            }
            i++;
        }
    }
    
    /**
     * Cambia el valor del titulo del libro que ocupa la posicion indicada por parametro.
     * @param titulo El nuevo titulo.
     * @param id El ID del libro a modificar.
     */
    public void setTitulo(int id, String titulo) {
        int i = 0;
        while(i < libros.size()) {
             Libro libroActual = libros.get(i);
             if(libroActual.getID() == id){
                    libros.get(i).setTitulo(titulo);
                    i = libros.size();
             }
             i++;
        }
    }
    
    /**
     * Cambia el valor del autor del libro que ocupa la posicion indicada por parametro.
     * @param autor El nuevo autor.
     * @param id El ID del libro a modificar.
     */
    public void setAutor(int id, String autor) {
        int i = 0;
        while(i < libros.size()) {
             Libro libroActual = libros.get(i);
             if(libroActual.getID() == id){
                    libros.get(i).setAutor(autor);
                    i = libros.size();
             }
             i++;
         }
    }
    
    /**
     * Cambia el valor del ano del libro que ocupa la posicion indicada por parametro.
     * @param ano El nuevo ano.
     * @param id El ID del libro a modificar.
     */
    public void setAno(int id, int ano) {
        int i = 0;
        while(i < libros.size()) {
             Libro libroActual = libros.get(i);
             if(libroActual.getID() == id){
                    libros.get(i).setAno(ano);
                    i = libros.size();
             }
             i++;
        }
    }
    
     /**
     * Cambia el valor del numero de paginas del libro que ocupa la posicion indicada por parametro.
     * @param numPags El nuevo numero de paginas.
     * @param id El ID del libro a modificar.
     */
    public void setNumPags(int id, int numPags) {
        int i = 0; 
        while(i < libros.size()) {
             Libro libroActual = libros.get(i);
             if(libroActual.getID() == id){
                    libros.get(i).setNumPags(numPags);
                    i = libros.size();
             }
             i++;
         }
    }
    
    /**
     * Borra todos los elementos del array cuyo ano sea el indicado por parametro.
     * @param anoBuscado El ano del que se quieren eliminar todos los libros.
     */
    public void borrarLibrosDelAno(int anoBuscado){
       Iterator<Libro> it = libros.iterator();
       while(it.hasNext()) {
           Libro libro = it.next();
           if(libro.getAno() == anoBuscado) {
               it.remove();
           }
       }
    }
    
}
