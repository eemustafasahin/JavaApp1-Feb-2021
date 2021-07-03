/*----------------------------------------------------------------------------------------------------------------------
    Pair sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util;

public final class Pair<F, S> {
    private final F m_first;
    private final S m_second;

    public static <F, S> Pair<F, S> of(F first, S second)
    {
        return new Pair<>(first, second);
    }

    public Pair(F first, S second)
    {
        m_first = first;
        m_second = second;
    }

    public F getFirst()
    {
        return m_first;
    }

    public S getSecond()
    {
        return m_second;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Pair))
            return false;

        var other = (Pair<F, S>)obj;

        return m_first.equals(other.m_first) && m_second.equals(other.m_second);
    }

    @Override
    public String toString()
    {
        return String.format("{first: %s, second: %s}", m_first, m_second);
    }
}
