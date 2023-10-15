package com.tg.manager.utils;

import com.tg.manager.view.StudentMock;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<StudentMock, Boolean> {
    private final Button viewButton;

    public ButtonCell() {
        this.viewButton = new Button("Ação");
        viewButton.setOnAction(event -> {
            StudentMock student = (StudentMock) getTableRow().getItem();
            if (student != null) {
                // Ação ao clicar no botão (pode ser personalizada)
                System.out.println("Botão clicado para: " + student.getStudent());
            }
        });
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(viewButton);
        }
    }
}
