package toko;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcMain {
  public static void main(String[] a)
      throws Exception {
    Server server = ServerBuilder
        .forPort(50051)
        .addService(new InventoryService())
        .build().start();
    server.awaitTermination();
  }
}