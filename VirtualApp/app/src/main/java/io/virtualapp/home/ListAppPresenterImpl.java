package io.virtualapp.home;

import android.app.Activity;
import android.content.Intent;

import java.io.File;

import io.virtualapp.VCommends;
import io.virtualapp.home.repo.AppDataSource;
import io.virtualapp.home.models.PackageAppData;
import io.virtualapp.home.repo.AppRepository;
import org.jdeferred.DoneCallback;
import io.virtualapp.home.models.AppInfo;
import java.util.List;

/**
 * @author Lody
 */
class ListAppPresenterImpl implements ListAppContract.ListAppPresenter {

	private Activity mActivity;
	private ListAppContract.ListAppView mView;
	private AppDataSource mRepository;

	private File from;

	ListAppPresenterImpl(Activity activity, ListAppContract.ListAppView view, File fromWhere) {
		mActivity = activity;
		mView = view;
		mRepository = new AppRepository(activity);
		mView.setPresenter(this);
		this.from = fromWhere;
	}

	@Override
	public void start() {
		mView.setPresenter(this);
		mView.startLoading();
		if (from == null)
			mRepository.getInstalledApps(mActivity).done(new DoneCallback() {

					@Override
					public void onDone(Object p1) {
						mView.loadFinish((List<AppInfo>)p1);
					}

				
			});
		else
			mRepository.getStorageApps(mActivity, from).done(new DoneCallback() {
					@Override
					public void onDone(Object p1) {
						mView.loadFinish((List<AppInfo>)p1);
					}
				});
	}
}
