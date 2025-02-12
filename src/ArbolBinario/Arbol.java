package ArbolBinario;

public class Arbol {
    private Nodo raiz;
    
    public Arbol(){
        this.raiz = null;
    }
    
    public boolean vacio(){
        return raiz == null;
    }
    
    public void agregar (String nombre){
        raiz = agregarNodo(raiz, nombre);
    }
    
    private Nodo agregarNodo (Nodo actual, String nombre){
        if (actual == null){
            return new Nodo(nombre);
        }
        if (nombre.compareTo(actual.nombre) < 0){
            actual.izq = agregarNodo(actual.izq, nombre);
        }
        else if (nombre.compareTo(actual.nombre) > 0){
            actual.der = agregarNodo(actual.der, nombre);
        }
        return actual;
    }
    public Nodo buscarNodo(String nombre){
        return encontrarNodo(raiz, nombre);
    }
    
    private Nodo encontrarNodo(Nodo actual, String nombre){
        if (actual == null || actual.nombre.equals(nombre)){
            return actual;
        }
        if (nombre.compareTo(actual.nombre) < 0){
            return encontrarNodo(actual.izq, nombre);
        }
        return encontrarNodo(actual.der, nombre); 
    }

}
