@extends('app')

@section('content')

<a href="/assets/add">Add</a>

@foreach ($AC as $index => $ACode) 
    
<ul>
	<li>{{$ACode->ID}}  <a href="/assets/edit/{{ $ACode->ID }}">{{$ACode->assetcode}}</a></li>

</ul>
@endforeach


@endsection