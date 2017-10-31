package com.example.ana.subjectmanager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransitionFragment extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String subjectName;

    private static final String ARG_PARAM = "";

    public TransitionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transition, container, false);

        subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName);

        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout)contenedor.findViewById(R.id.appbar);
        tabLayout = new TabLayout(getActivity());

        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabLayout);

        viewPager = (ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //tabLayout.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter (FragmentManager fragmentManager) {
            super (fragmentManager);
        }
        String [] tituloTabs= {"Videos", "Links", "PDFs"};

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: return new Tab_VideosFragment(subjectName);
                case 1: return new Tab_LinksFragment(subjectName);
                case 2: return new Tab_PdfsFragment(subjectName);
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tituloTabs[position];
        }
    }

    public static TransitionFragment newInstance(String param) {
        TransitionFragment fragment = new TransitionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }
}
