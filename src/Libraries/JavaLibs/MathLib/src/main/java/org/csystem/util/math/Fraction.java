/*----------------------------------------------------------------------------------------------------------------------
	Fraction sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.math;

public class Fraction implements Comparable<Fraction> {
    private int m_a;
    private int m_b;

    private static Fraction add(int a1, int b1, int a2, int b2)
    {
        return new Fraction(a1 * b2 + a2 * b1, b1 * b2);
    }

    private static Fraction subtract(int a1, int b1, int a2, int b2)
    {
        return add(a1, b1, -a2, b2);
    }

    private static Fraction multiply(int a1, int b1, int a2, int b2)
    {
        return new Fraction(a1 * a2, b1 * b2);
    }

    private static Fraction divide(int a1, int b1, int a2, int b2)
    {
        return multiply(a1, b1, b2, a2);
    }

    private static void check(int a, int b)
    {
        if (b == 0) {
            if (a == 0)
               throw new FractionException("Indefinite", FractionExceptionStatus.INDEFINITE, a, b);

            throw new FractionException("Undefined", FractionExceptionStatus.UNDEFINED, a, b);
        }
    }

    private void simplify()
    {
        int min = Math.min(Math.abs(m_a), m_b);

        for (int i = min; i >= 2; --i)
            if (m_a % i == 0 && m_b % i == 0) {
                m_a /= i;
                m_b /= i;
                break;
            }
    }

    private void setSign()
    {
        if (m_b < 0) {
            m_a = -m_a;
            m_b = -m_b;
        }
    }

    private void set(int a, int b)
    {
        if (a == 0) {
            m_a = 0;
            m_b = 1;
            return;
        }
        m_a = a;
        m_b = b;
        setSign();
        simplify();
    }

    public Fraction()
    {
        m_b = 1;
    }

    public Fraction(int a)
    {
        m_a = a;
        m_b = 1;
    }

    public Fraction(int a, int b)
    {
        check(a, b);
        this.set(a, b);
    }

    public int getNumerator()
    {
        return m_a;
    }

    public void setNumerator(int val)
    {
        if (val == m_a)
            return;

        this.set(val, m_b);
    }

    public int getDenominator()
    {
        return m_b;
    }

    public void setDenominator(int val)
    {
        if (val == m_b)
            return;

        check(m_a, val);
        this.set(m_a, val);
    }

    public double getRealValue()
    {
        return (double) m_a / m_b;
    }

    //add methods
    public static Fraction add(int val, Fraction f)
    {
        return add(val, 1, f.m_a, f.m_b);
    }

    public Fraction add(Fraction other)
    {
        return add(m_a, m_b, other.m_a, other.m_b);
    }

    public Fraction add(int val)
    {
        return add(m_a, m_b, val, 1);
    }

    //subtract methods
    public static Fraction subtract(int val, Fraction f)
    {
        return subtract(val, 1, f.m_a, f.m_b);
    }

    public Fraction subtract(Fraction other)
    {
        return subtract(m_a, m_b, other.m_a, other.m_b);
    }

    public Fraction subtract(int val)
    {
        return subtract(m_a, m_b, val, 1);
    }

    //multiply methods
    public static Fraction multiply(int val, Fraction f)
    {
        return multiply(val, 1, f.m_a, f.m_b);
    }

    public Fraction multiply(Fraction other)
    {
        return multiply(m_a, m_b, other.m_a, other.m_b);
    }

    public Fraction multiply(int val)
    {
        return multiply(m_a, m_b, val, 1);
    }

    //divide methods
    public static Fraction divide(int val, Fraction f)
    {
        return divide(val, 1, f.m_a, f.m_b);
    }

    public Fraction divide(Fraction other)
    {
        return divide(m_a, m_b, other.m_a, other.m_b);
    }

    public Fraction divide(int val)
    {
        return divide(m_a, m_b, val, 1);
    }

    //increment
    public void increment(int val)
    {
        m_a += m_b * val;
    }

    public void increment()
    {
        this.increment(1);
    }

    //decrement
    public void decrement(int val)
    {
        this.increment(-val);
    }

    public void decrement()
    {
        this.decrement(1);
    }

    @Override
    public int compareTo(Fraction other)
    {
        return m_a * other.m_b - other.m_a * m_b;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Fraction))
            return false;

        Fraction other = (Fraction)obj;

        return m_a == other.m_a && m_b == other.m_b;
    }

    @Override
    public String toString()
    {
        return String.format("%d%s", m_a, m_b == 1 ? "" : String.format(" / %s = %f", m_b, getRealValue()));
    }
}
