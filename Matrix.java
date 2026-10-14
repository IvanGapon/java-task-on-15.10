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
        for ( int i = 0 ; i < data.length  ; i++ ){
            if ( data[i].length != data[0].length ){
                throw new IllegalArgumentException(" все строки должны быть одинаковой длины");
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
            throw new IllegalArgumentException("индексы вне диапазаона");
        }

        return new Complex(data[i][j]);
    }
    public void set(int i, int j, Complex value) {
        if ( j < 0 || j >= cols || i < 0 || i >= rows ){
            throw new IllegalArgumentException("индексы вне диапазаона");
        }

        data[i][j] = new Complex(value);
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = new Complex(data[i][j]);
            }
        }
        return result;
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

    public Matrix minus(Matrix other) {
       if (this.cols != other.cols || this.rows != other.rows ) {
            throw new IllegalArgumentException("Матрицф не одного размера");
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].minus(other.data[i][j]);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("число строк первой матрицы должно быть равно числу столбцов второй");
        }

        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(); 

                for (int k = 0; k < this.cols; k++) {
                    Complex prod = new Complex(data[i][k]);
                    sum = sum.plus(prod.multiply(other.data[k][j]));
                }

                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public Complex determinant(){
        if (rows != cols) {
            throw new IllegalArgumentException("матрица не квадратная");
        }
        return consid_determ( this.data );
    }
    private Complex consid_determ ( Complex[][] matrix ) {
        int n = matrix.length;
        if (n == 1) return new Complex(matrix[0][0]);
        if (n == 2) return matrix[0][0].multiply(matrix[1][1]).minus(matrix[0][1].multiply(matrix[1][0]));

        Complex det = new Complex();
        for (int j = 0; j < n; j++) {
            if (matrix[0][j].isZero()) continue; 

            Complex[][] minor = new Complex[n - 1][n - 1];
            for (int i = 1; i < n; i++) {
                int colmin = 0;
                for (int k = 0; k < n; k++) {
                    if (k == j) continue;
                    minor[i - 1][colmin] = new Complex(matrix[i][k]);
                    colmin++;
                }
            }

            Complex temp = matrix[0][j].multiply(consid_determ(minor));
            if ( j % 2 == 1 ){
                det = det.minus(temp);
            }
            else {
                det = det.plus(temp);
            }
        }
        return det;
    }

    public Matrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException("матрица не квадратная");
        }

        int n = rows ; 
        Complex det = determinant();
        if (det.isZero()) {
            throw new ArithmeticException("определител 0, обратная матрица не существует");
        }

        Matrix result = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                Complex[][] minor = new Complex[n - 1][n - 1];
                int m_i = 0;
                for (int r = 0; r < n; r++) {
                    if (r == i) continue;
                    int m_j = 0;
                    for (int c = 0; c < n; c++) {
                        if (c == j) continue;
                        minor[m_i][m_j] = data[r][c];
                        m_j++;
                    }
                    m_i++;
                }

                Complex cof = consid_determ(minor);
                if ((i + j) % 2 == 1) {
                    cof = new Complex(-cof.getdeistv(), -cof.getmnim());
                }

                result.data[j][i] = cof.divide(det);
            }
        }

        return result;
    }
    
    public Matrix divide(Matrix y) {
        if (y.rows != y.cols) {
            throw new IllegalArgumentException("делитель должен быть квадратной матрицей");
        }
        if (this.cols != y.rows) {
            throw new IllegalArgumentException("число столбцов первой матрицы должно быть равно числу строк второй");
        }


        return this.multiply(y.inverse());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j].toString());
                if (j < cols - 1) sb.append(",\t");
            }
            sb.append(" ]");
            if (i < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (this.rows != other.rows || this.cols != other.cols) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!this.data[i][j].equals(other.data[i][j])) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + rows;
        result = 31 * result + cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result = 31 * result + data[i][j].hashCode();
            }
        }
        return result;
    }

}