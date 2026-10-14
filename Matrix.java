public class Matrix {
    private Complex[][] data;
    private int rows;
    private int cols;

    public Matrix(int z_rows, int z_cols) {
        if ( z_cols <= 0 || z_rows <= 0 ){
            throw new IllegalArgumentException("некорректные размеры матрицы");
        }

        this.cols = z_cols;
        this.rows = z_rows;
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex();
            }
        }
    }

    public Matrix(Complex[][] data) {
        if (data[0].length * data.length == 0) {
            throw new IllegalArgumentException("некорректные размеры матрицы");
        }
        for ( int i = 0 ; i < rows  ; i++ ){
            if ( data[i].length != data[0].length ){
                throw new IllegalArgumentException("Все строки должны быть одинаковой длины");
            }
        }

        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = new Complex(data[i][j]);
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public Complex get(int i, int j) {
        if ( j < 0 || j >= cols || i < 0 || i >= rows ){
            throw new IllegalArgumentException("Индексы вне диапазаона");
        }

        return new Complex(data[i][j]);
    }

    public void set(int i, int j, Complex value) {
        if ( j < 0 || j >= cols || i < 0 || i >= rows ){
            throw new IllegalArgumentException("Индексы вне диапазаона");
        }

        data[i][j] = new Complex(data[i][j]);
    }

    public Matrix plus(Matrix other) {
        if (this.cols != other.cols || this.rows != other.rows ) {
            throw new IllegalArgumentException("Матрицф не одного размера");
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].plus(other.data[i][j]);
            }
        }

        return result;
    }
}