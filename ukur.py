import gzip
import json

import measure_pb2 as m


def js(obj):
    return json.dumps(obj, separators=(",", ":")).encode()


def tampil(nama, j, p):
    hemat = (1 - len(p) / len(j)) * 100
    print(f"{nama}: JSON {len(j)} B, Protobuf {len(p)} B, hemat {hemat:.0f}%")


tampil("StockReply", js({"quantity": 12}),
       m.StockReply(quantity=12).SerializeToString())
tampil("Product", js({"id": 42, "name": "Keyboard"}),
       m.Product(id=42, name="Keyboard").SerializeToString())

data = [(i, f"Produk {i}") for i in range(1, 101)]
j = js([{"id": i, "name": n} for i, n in data])
p = m.ProductList(
    products=[m.Product(id=i, name=n) for i, n in data]
).SerializeToString()
tampil("100 produk", j, p)
print("gzip 100 produk:", len(gzip.compress(j)), "vs", len(gzip.compress(p)))