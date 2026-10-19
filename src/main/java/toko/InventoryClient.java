package toko;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import toko.grpc.InventoryGrpc;
import toko.grpc.StockReply;
import toko.grpc.StockRequest;

import java.util.concurrent.TimeUnit;

public class InventoryClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            InventoryGrpc.InventoryBlockingStub stub =
                    InventoryGrpc.newBlockingStub(channel)
                            .withDeadlineAfter(2, TimeUnit.SECONDS);

            StockRequest request = StockRequest.newBuilder()
                    .setProductId(42)
                    .build();

            StockReply reply = stub.getStock(request);
            System.out.println("Stok produk: " + reply.getQuantity());

        } catch (StatusRuntimeException e) {
            Status.Code code = e.getStatus().getCode();
            System.out.println("gRPC gagal: " + code);

            if (code == Status.Code.UNAVAILABLE) {
                System.out.println("Layanan tidak tersedia; pemanggilan dapat dicoba ulang.");
            } else if (code == Status.Code.DEADLINE_EXCEEDED) {
                System.out.println("Batas waktu 2 detik terlampaui.");
            }
        } finally {
            channel.shutdown();
        }
    }
}
