@extends('app')

@section('content')

<a href ="/maintainence/add">Add </a>
@foreach ($Maintainence as $ACode) 
<ul>

    <li>{{$ACode->mnid}} {{$ACode->Ecode}}  {{$ACode->Name}}</li>

</ul>

@endforeach


@endsection