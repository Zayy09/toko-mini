package toko;

import io.grpc.stub.StreamObserver;
import toko.grpc.InventoryGrpc;
import toko.grpc.StockReply;
import toko.grpc.StockRequest;

public class InventoryService
    extends InventoryGrpc.InventoryImplBase {
  @Override
  public void getStock(StockRequest req,
      StreamObserver<StockReply> out) {
    out.onNext(StockReply.newBuilder()
        .setQuantity(12).build());
    out.onCompleted();
  }
}