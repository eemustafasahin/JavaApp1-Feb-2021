package org.csystem.data.factory;

import org.csystem.data.product.ProductInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class MyProductFactory {
    public final List<ProductInfo> PRODUCTS = new ArrayList<>();

    private static String join(String [] strings, int startIndex, int endIndex, String delimiter) //İleride tek hamlede yapacağız
    {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < endIndex; ++i)
            sb.append(strings[i]).append(delimiter);

        return sb.substring(0, sb.length() - delimiter.length());
    }

    private static ProductInfo getProduct(String line)
    {
        var productsInfo = line.split("[,]");
        var id = Integer.parseInt(productsInfo[0]);
        var name = join(productsInfo, 1, productsInfo.length - 3, ",");
        var stock = Integer.parseInt(productsInfo[productsInfo.length - 3]);
        var cost = new BigDecimal(productsInfo[productsInfo.length - 2]);
        var price = new BigDecimal(productsInfo[productsInfo.length - 1]);

        return new ProductInfo().setId(id).setName(name).setPrice(price).setCost(cost).setStock(stock);
    }

    private MyProductFactory()
    {}

    public static MyProductFactory loadFromTextFile(String path) throws IOException
    {
        var result = new MyProductFactory();

        try (var br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            if (br.readLine() == null)
                return result;

            String line;

            while ((line = br.readLine()) != null)
                result.PRODUCTS.add(getProduct(line));
        }

        return result;
    }

    public Iterable<ProductInfo> getProductsAsIterable()
    {
        return PRODUCTS;
    }

    public Optional<ProductInfo> getRandomProduct(Random r)
    {
        return PRODUCTS.isEmpty() ? Optional.empty() : Optional.of((ProductInfo)PRODUCTS.get(r.nextInt(PRODUCTS.size())).clone());
    }

    public Optional<ProductInfo> getRandomDiffNameProduct(Random r)
    {
        if (PRODUCTS.isEmpty())
            return Optional.empty();

        var productInfo = (ProductInfo)PRODUCTS.get(r.nextInt(PRODUCTS.size())).clone();

        if (r.nextBoolean())
            productInfo.setName(productInfo.getName().toUpperCase());

        return Optional.of(productInfo);
    }
}
