package com.company;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// Наша модель содержит 3 столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        Double res;
        double x = from + step*row;
        if (col==0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        }
        // Если запрашивается значение 2-го столбца, то это значение многочлена
        if (col == 1) {
            res = coefficients[0];
            for (int i = 0; i < coefficients.length - 1; i++) {
                res = res * x + coefficients[i + 1];
            }
            return res;
        }
        else {
//Для 3-го столбца
            res = coefficients[0];
            Boolean boolResult;
            for (int i = 0; i < coefficients.length - 1; i++) {
                res = res * x + coefficients[i + 1];
            }
            //Возвращает true, если целая часть - квадрат
            Double temp = Math.pow(Math.round(Math.sqrt(res)), 2);
            if (res.intValue()==temp.intValue()) {
                boolResult = true;
            } else {
                boolResult = false;
            }
            return boolResult;
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            default:
// Название 3-го столбца
                return "Целая часть является квадратом";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        if (col!=2) {
            return Double.class;
        } else {
            return Boolean.class;
        }
    }
}

