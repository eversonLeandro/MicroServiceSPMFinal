package co.edu.unicauca.infra;

import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.services.PostulationService;
import co.edu.unicauca.infra.ButtonEditor;
import co.edu.unicauca.infra.ViewProjectAction;
import javax.swing.JTable;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.domain.services.StudentService;
import java.util.List;

public class ButtonEditorFactory {

    private static final Map<String, Function<Object[], ButtonEditor<?>>> buttonEditors = new HashMap<>();

    public static void registerButtonEditor(String type, Function<Object[], ButtonEditor<?>> creator) {
        buttonEditors.put(type, creator);
    }

    public static ButtonEditor<?> createButtonEditor(String type, Object... args) {
        Function<Object[], ButtonEditor<?>> creator = buttonEditors.get(type);
        if (creator != null) {
            return creator.apply(args);
        }
        throw new IllegalArgumentException("Tipo de botón no válido: " + type);
    }

    static {

        // Registrar botón "ver"
        registerButtonEditor("ver", args -> {
            try {
                return new ButtonEditor<verProyectPostuladoContext>(
                        (JTable) args[0],
                        new verProyectPostuladoContext(
                                (JTable) args[0],
                                (JFrame) args[1],
                                (IFrameEventListener) args[2],
                                (ProjectService) args[3]
                        ),
                        new ViewProjectAction(),
                        "Ver"
                );
            } catch (Exception e) {
                System.err.println("Error al crear el botón 'ver': " + e.getMessage());
                e.printStackTrace();
                throw e; // Relanza la excepción para no ocultarla
            }
        });

        // Registrar botón "aprobar"
        registerButtonEditor("aprobar", args -> {
            try {
                return new ButtonEditor<PostularseContext>(
                        (JTable) args[0],
                        new PostularseContext(
                                (JTable) args[0],
                                (JFrame) args[1],
                                (IFrameEventListener) args[2],
                                (String) args[3],
                                (StudentService) args[4],
                                (PostulationService) args [5]
                        ),
                        new postularseAction(),
                        "Postular"
                );
            } catch (Exception e) {
                System.err.println("Error al crear el botón 'aprobar': " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        });

    }

}
