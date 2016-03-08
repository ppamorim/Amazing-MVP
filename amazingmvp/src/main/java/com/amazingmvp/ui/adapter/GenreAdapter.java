package com.amazingmvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import com.amazingmvp.ui.callback.GenreCallback;
import com.amazingmvp.ui.fragment.GenreFragment;

public class GenreAdapter extends FragmentPagerAdapter {

  private ArrayMap<Integer, GenreFragment> fragments = null;
  private String[] titles;

  public GenreAdapter(FragmentManager fragmentManager,
      int count,String[] titles, GenreCallback genreCallback) {
    super(fragmentManager);
    generateFragmentsIfNeeded(count, titles, genreCallback);
  }

  /**
   * This method will return the Fragment instance based on
   * the position in the ArrayList.
   * @param position Position of item in ArrayList.
   * @return Fragment instance. If the ArrayList is null,
   * return null;
   */
  @Override public Fragment getItem(int position) {
    return fragments != null ? fragments.get(position) : null;
  }

  /**
   * This method is used to count the size of the ArrayList
   * of Fragments. If null, return 0.
   * @return The size of the ArrayList of Fragments.
   */
  @Override public int getCount() {
    return fragments != null ? fragments.size() : 0;
  }

  /**
   * The title needs only 3 characters to be configured.
   * @param position Position of the fragment.
   * @return The configured title or the super of the method.
   */
  @Override public CharSequence getPageTitle(int position) {
    return titles != null ? titles[position] : super.getPageTitle(position);
  }

  public void invalidate() {
    if (fragments != null) {
      fragments.clear();
    }
    fragments = null;
  }

  /**
   * This method will generate the fragments to be used by
   * the ViewPager.
   * Based on the position, this method will name the titles
   * and configure the accentColor of this fragment.
   * @param count Items on the success of the call of Interactor.
   * @param titles Possible title to be used on the TabLayout.
   */
  public void generateFragmentsIfNeeded(int count, String[] titles, GenreCallback genreCallback) {
    if (fragments == null) {
      this.titles = titles;
      fragments = new ArrayMap<>(count);
      for(int i = 0; i < count; i++) {
        fragments.put(i, GenreFragment.newInstance(genreCallback));
      }
    }
  }

}