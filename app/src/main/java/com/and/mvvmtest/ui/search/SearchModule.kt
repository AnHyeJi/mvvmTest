package com.and.mvvmtest.ui.search

import com.and.mvvmtest.adapter.SearchUiAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SearchModule {

    //이건 대거 예시 참고*
    @Provides
    @Named("dataInput") //리턴 타입이 String으로 같은 경우가 많을수 있기때문에 네이밍 지정
    fun provideString() : String {
        return "데이터 입력"
    }
}