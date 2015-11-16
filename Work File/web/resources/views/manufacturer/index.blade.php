@extends('app')

@section('content')

<a href="/manufacturer/add">Add</a>

@foreach ($Manufacturer as $ACode) 
    
<ul>
    <li>{{$ACode->Mid}}{{$ACode->Name}}</li>
</ul>

@endforeach


@endsection