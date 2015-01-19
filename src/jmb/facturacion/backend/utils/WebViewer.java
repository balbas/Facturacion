/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author jmbalbas
 */
public class WebViewer extends JEditorPane {
    /**
     * Construye una instancia de esta clase.
     * Es un JEditorPane preparado para html, no editable.
     */
    public WebViewer() {
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        setEditorKitForContentType("text/html", htmlEditorKit);
        setEditable(false);
        htmlEditorKit.install(this);
        addHyperlinkListener(listener);
    }
    
    /**
     * Listener para el tratamiento del click en los hipervínculos, haciendo
     * que el navegador muestre la página apuntada por el hipervínculo.
     */
    private final HyperlinkListener listener = new HyperlinkListener() {
        /** 
         * Nos llamarán aquí cada vez que hay cualquier evento de ratón sobre
         * un hipervínculo: movimiento del ratón por encima, clicks, etc.
         */
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) {
            try {
                // Se comprueba si se ha hecho click
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    // Se obtiene la url a la que apunta el hiperlink
                    if (e.getURL() != null) {
                        // Se pasa la página al editor
                        setPage(e.getURL().toString());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
}
