package com.myapp.service;

import com.myapp.model.dao.AssetDao;
import com.myapp.model.vo.Asset;
import static com.myapp.common.Template.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssetService {
    private AssetDao assetDao = new AssetDao();
    private Random random = new Random();

    // 메모리용 리스트
    private final List<Asset> assets = new ArrayList<>();

    public AssetService() {
        // DB에서 초기 로딩
        assets.addAll(selectAllAsset());

        // 가격 갱신 스레드
        Thread priceUpdater = new Thread(() -> {
            while (true) {
                try {
                    synchronized (assets) { // 스레드 안전
                        for (Asset asset : assets) {
                            simulatePriceChange(asset);
                        }
                    }
                    Thread.sleep(1000); // 1초마다
                } catch (InterruptedException e) {
                    break; // 스레드 종료
                } catch (Exception e) {
                    e.printStackTrace(); // DB 예외 등
                }
            }
        });
        priceUpdater.setDaemon(true); // 프로그램 종료 시 같이 종료됨
        priceUpdater.start();
    }

    public Asset selectAsset(String assetId) {
        Connection conn = getConnection();
        Asset asset = assetDao.selectAsset(new Asset(assetId), conn);
        close(conn);
        return asset;
    }

    public List<Asset> selectAllAsset() {
        Connection conn = getConnection();
        List<Asset> list = assetDao.selectAllAsset(conn);
        close(conn);
        return list;
    }

    public void updateAssetPrice(Asset asset) {
        Connection conn = getConnection();
        assetDao.updatePrice(asset, conn);
        commit(conn);
        close(conn);
    }

    // 메모리 내 가격 시뮬레이션 (UI 전용)
    public void simulatePriceChange(Asset asset) {
        double lastPrice = asset.getCurrentPrice();
        double vol = asset.getVolatility() / 100.0;

        double drift = 0.0005; // 평균 상승 0.05%

        double noise = random.nextGaussian() * vol;
        noise = Math.max(-0.002, Math.min(0.002, noise)); // -0.2% ~ +0.2%

        double spike = 0.0;
        if (random.nextDouble() < 0.005)
            spike = lastPrice * (0.02 + random.nextDouble() * 0.03);

        double change = lastPrice * (drift + noise) + spike;
        asset.setCurrentPrice(Math.max(1, lastPrice + change));

        updateAssetPrice(asset);
    }

}
